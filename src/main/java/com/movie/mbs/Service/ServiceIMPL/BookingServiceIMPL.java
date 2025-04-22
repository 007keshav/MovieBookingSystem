    package com.movie.mbs.Service.ServiceIMPL;

    import com.movie.mbs.DTO.BookingDTO;
    import com.movie.mbs.Entity.Booking;
    import com.movie.mbs.Entity.BookingStatus;
    import com.movie.mbs.Entity.Shows;
    import com.movie.mbs.Entity.User;
    import com.movie.mbs.Repository.BookingRepository;
    import com.movie.mbs.Repository.ShowRepository;
    import com.movie.mbs.Repository.UserRepository;
    import com.movie.mbs.Service.BookingService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Set;
    import java.util.stream.Collectors;

    @Service
    public class BookingServiceIMPL implements BookingService {

        @Autowired
        private ShowRepository showRepository;


        @Autowired
        private BookingRepository bookingRepository;


        @Autowired
        private UserRepository userRepository;

        @Override
        public Booking createBooking(BookingDTO bookingDTO) {
            Shows show = showRepository.findById(bookingDTO.getShowId())
                    .orElseThrow(() -> new RuntimeException("Show not found"));

            if(!isSeatsAvailable(show.getId(), bookingDTO.getNumberOfSeats())){
                throw new RuntimeException("not enough seats are available");
            }

            if(bookingDTO.getSeatNumbers().size() != bookingDTO.getNumberOfSeats()){
                throw new RuntimeException("seat numbers and number of seats must be equals");
            }

            validateDuplicateSeats(show.getId(), bookingDTO.getSeatNumbers());

            User user = userRepository.findById(bookingDTO.getUserId())
                    .orElseThrow(()-> new RuntimeException("User not found"));

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setShow(show);
            booking.setNumberOfSeats(bookingDTO.getNumberOfSeats());
            booking.setSeatNumbers(bookingDTO.getSeatNumbers());
            booking.setPrice(calculateTotalAmount(show.getPrice(), bookingDTO.getNumberOfSeats()));
            booking.setBookingStatus(BookingStatus.PENDING);
            booking.setBookingTime(LocalDateTime.now());

            return bookingRepository.save(booking);
        }

        public boolean isSeatsAvailable(Long showid, Integer numberOfSeats){
            Shows show = showRepository.findById(showid)
                    .orElseThrow(()-> new RuntimeException("show not found"));

            int bookedSeats=show.getBookings().stream()
                    .filter(booking-> booking.getBookingStatus()!= BookingStatus.CANCELLED)
                    .mapToInt(Booking::getNumberOfSeats)
                    .sum();
            return (show.getTheater().getTheaterCapacity() - bookedSeats) >= numberOfSeats;
        }

        public void validateDuplicateSeats(Long showId, List<String> seatNumbers){
            Shows show =  showRepository.findById(showId)
                    .orElseThrow(() -> new RuntimeException( " show not found"));

            Set<String> occupiedSeats = show.getBookings().stream()
                    .filter(b->b.getBookingStatus() != BookingStatus.CANCELLED)
                    .flatMap(b->b.getSeatNumbers().stream())
                    .collect(Collectors.toSet());
            List<String> duplicateSeats = seatNumbers.stream()
                    .filter(occupiedSeats::contains)
                    .toList();

            if(!duplicateSeats.isEmpty()){
                throw  new RuntimeException("seats are already booked");
            }
        }

        public Double calculateTotalAmount(Double price, Integer numberOfSeats){
            return price*numberOfSeats;
        }



        @Override
        public List<Booking> getUserBooking(Long id) {
            return bookingRepository.findByUserId(id);
        }

        @Override
        public List<Booking> getShowBooking(Long id) {
            return bookingRepository.findByShowId(id);
        }

        @Override
        public Booking confirmBooking(long id) {
            Booking booking = bookingRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("booking not found"));

            if(booking.getBookingStatus() != BookingStatus.PENDING){
                throw new RuntimeException("Booking is not in pending state");
            }

            //payment api process
            booking.setBookingStatus(BookingStatus.CONFIRMED);

            return bookingRepository.save(booking);
        }

        @Override
        public Booking cancelBooking(long id) {
            Booking booking = bookingRepository.findById(id)
                    .orElseThrow(()-> new RuntimeException("booking not found"));

            validateCancellation(booking);
            booking.setBookingStatus(BookingStatus.CANCELLED);
            return bookingRepository.save(booking);
        }

        @Override
        public List<Booking> getBookingsByStatus(BookingStatus bookingStatus) {
            return bookingRepository.findByBookingStatus(bookingStatus);
        }


        public void validateCancellation(Booking booking){
            LocalDateTime showTime = booking.getShow().getShowTime();
            LocalDateTime deadLineTime = showTime.minusHours(2);

            if(LocalDateTime.now().isAfter(deadLineTime)){
                throw new RuntimeException("cannot cancel the booking");
            }

            if(booking.getBookingStatus() == BookingStatus.CANCELLED){
                throw new RuntimeException("booking already cancelled");
            }
        }
    }

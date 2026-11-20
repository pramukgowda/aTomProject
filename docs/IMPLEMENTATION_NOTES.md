# Implementation notes

- `Guest` validates names and phone numbers.
- `Room` stores rates as `BigDecimal`.
- `Booking` enforces WAITING → ALLOCATED → CHECKED_IN → CHECKED_OUT; cancellation is allowed from WAITING or ALLOCATED.
- `RoomService` owns inventory, allocation and release.
- `ReservationService` coordinates reservations, cancellation and FIFO promotion.
- `BookingService` enforces check-in/check-out and reports revenue.
- `TariffService` uses HALF_UP rounding, 10% discount for 5+ nights and 12% GST.
- `WaitingListQueue` is a custom linked FIFO queue; room-type removals preserve other guests' relative order.
- Regression tests cover allocation, FIFO behavior, promotion, checkout state, billing, cancellation and validation.

No persistence, date-range availability, concurrent-user handling, payments, accounts or notifications are included.

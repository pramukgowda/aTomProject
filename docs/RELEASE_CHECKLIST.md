# Release checklist

- [x] Compiles with Java 17 without warnings.
- [x] Validates guest details, room type and positive stay lengths.
- [x] Enforces booking lifecycle transitions.
- [x] Allocates first vacant room.
- [x] Maintains FIFO waiting-list order by room type.
- [x] Releases rooms on checkout and eligible cancellation.
- [x] Promotes waiting guests after room release.
- [x] Uses decimal-safe bill calculations.
- [x] Includes occupancy, booking and revenue reports.
- [x] Includes regression tests and Windows/Unix scripts.
- [ ] Run on target machine and confirm JDK 17 is installed.
- [ ] Add persistence and date-based reservations only if the assignment scope requires them.

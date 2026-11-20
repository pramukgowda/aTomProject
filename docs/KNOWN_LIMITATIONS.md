# Known limitations

1. Booking records are in memory and disappear after exit.
2. Inventory tracks occupied/vacant state only; future date ranges and overlapping reservations are not modelled.
3. Single-process demo; no concurrent users.
4. Guest/admin roles are menu demonstrations, not authenticated accounts.
5. Bills are displayed; no payment gateway, refunds or receipt archive.
6. No email/SMS notifications.
7. Inventory and rates are hard-coded.
8. Actual stay duration is entered manually at checkout.
9. Academic/demo project; do not store real customer information.

Production use requires a transactional database, date-aware availability, authentication/authorization, secure personal-data handling, payment/refund support, audit logging and monitoring.

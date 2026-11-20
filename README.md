# Hotel Room Booking System

Java 17+ command-line hotel booking application.

## Features
- Guest and admin menus.
- Single, Double and Suite room inventory with first-vacant allocation.
- FIFO waiting list, check-in, check-out, cancellation and waiting-list promotion.
- Decimal-safe billing: 10% discount for stays of 5+ nights followed by 12% GST.
- Occupancy, booking and revenue reports.
- Input validation and custom exceptions.

## Room inventory and rates
| Type | Room numbers | Rate per night |
|---|---|---:|
| Single | 101–104 | Rs. 1,500 |
| Double | 201–204 | Rs. 2,500 |
| Suite | 301–302 | Rs. 5,000 |

## Requirements
JDK 17 or newer. No external dependencies.

## Run and test
Linux/macOS: `bash run.sh` and `bash test.sh`.
Windows: `run.bat` and `test.bat`.

## Package JAR
Linux/macOS: `bash package.sh`; Windows: `package.bat`. Launch with `java -jar dist/HotelBookingSystem.jar`.

## Structure
`src/main/java/app` console UI; `model` domain classes; `service` booking and tariff services; `datastructures` FIFO waiting queue; `exceptions` custom exceptions; `util` helpers/reports; `src/test/java` regression tests.

## Limitations
Academic/demo console project. Booking records are in memory and disappear after exit. No date-range availability, persistence, real authentication, payment integration, notifications or hosted database. Do not use real customer data.

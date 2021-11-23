## Running

* Build and install the project using command:
   ```
    mvn install
   ```

* Run application locally using IDE **OR** go to the project directory and run command:
   ```
    java -jar target/tax-calculator-0.0.1-SNAPSHOT.jar
   ```

## How it works

* After running the application, send first POST request with JSON object representing the name of the company and input
  income using `curl`, for example:
   ```
   curl -X POST -H "Content-Type: application/json" -d "{\"name\" : \"company1\", \"income\" : 3000}" http://localhost:8080/entries
   ```
  Application will process all necessary calculations and store created TaxEntry object on `List<TaxEntry> taxEntryList`. 
  You can send the POST request with different data as many times as you need.

* Type url: http://localhost:8080/entries in your browser **OR** use `curl`:
  ```
   curl http://localhost:8080/entries
  ```
  Result is a JSON with the array of five last added objects representing TaxEntryDto class objects and logging in the
  console info message concerning the actual size of the list and detailed data for each entry. The array starts with
  the oldest entry.

* Shut down application in your IDE **OR** use `curl`:
  ```
   curl -X POST localhost:8080/actuator/shutdown
  ```
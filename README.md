Instructions
--
- `./run.sh /absolute/file/name/here.txt` will output a `output.txt` file in the parent directory with the results
- `./gradlew clean test` will run all tests

Assumptions
--
- Drone needs to be back at factory by 10PM
- Orders that are impossible to fulfill within the time requirements (ex: order made at 9:55PM and location is 20 minutes away) are ignored
- Orders that are made before the drone's starting time do not count against NPS (ex: order made at 1AM and fulfilled at 6:10AM does not count as a detractor)

Possible improvements
--
- More rigorous validations for input
- Better algorithm for maximizing NPS (https://stackoverflow.com/questions/30449023/efficient-implementation-of-multi-factor-weighted-sorting-in-java points out deficiency in current implementation)
- Currently handling location strictly as absolute distance from factory, therefore only positive numbers. But maybe `S5W3` should be interested as `(-5,-3)` to future proof
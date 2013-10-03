package com.example

import org.specs2.mutable.Specification

class ElevatorSpec extends Specification {

  "Elevator" should {

    "return NOTHING when it has nothing to do :)" in {
      Elevator().nextCommand().command must be equalTo "NOTHING"
    }

    "return UP when calling from the 1st floor" in {
      Elevator().call(1).nextCommand().command must be equalTo "UP"
    }

    "return DOWN when calling from the 1st floor and elevator is at 3rd floor" in {
      Elevator(currentFloor = 3).call(1).nextCommand().command must be equalTo "DOWN"
    }

    "return OPEN when calling from the 1st floor and elevator is at 1st floor" in {
      Elevator(currentFloor = 1).call(1).nextCommand().command must be equalTo "OPEN"
    }

    "return CLOSE after user has entered" in {
      Elevator(currentFloor = 1, isOpen = true).userHasEntered().nextCommand().command must be equalTo "CLOSE"
    }

    "return UP when calling from the 1st floor and elevator is at 3rd floor and has already been called at 4th" in {
      var elevator = Elevator(currentFloor = 1).call(2).call(3)

      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
    }
  }
}

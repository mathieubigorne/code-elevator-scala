package com.example

import org.specs2.mutable.Specification

class ElevatorSpec extends Specification {

  "Elevator" should {

    "return NOTHING when it has nothing to do :)" in {
      def elevator = new Elevator()
      elevator.nextCommand() must be equalTo "NOTHING"
    }

    "return UP when calling from the 1st floor" in {
      val elevator = new Elevator()
      elevator.call(1)
      elevator.nextCommand() must be equalTo "UP"
    }

    "return DOWN when calling from the 1st floor and elevator is at 3rd floor" in {
      val elevator = new Elevator(3)
      elevator.call(1)
      elevator.nextCommand() must be equalTo "DOWN"
    }

    "return OPEN when calling from the 1st floor and elevator is at 1st floor" in {
      val elevator = new Elevator(1)
      elevator.call(1)
      elevator.nextCommand() must be equalTo "OPEN"
    }

    "return CLOSE after user has entered" in {
      val elevator = new Elevator(1, true)
      elevator.userHasEntered()
      elevator.nextCommand() must be equalTo "CLOSE"
    }
  }
}

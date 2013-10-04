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

    "return UP twice when calling from the 2nd floor and 3rd floor and elevator is at 1st floor" in {
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

    "return DOWN twice when calling from the 3rd floor and 2nd floor and elevator is at 4rd floor" in {
      var elevator = Elevator(currentFloor = 4).call(3).call(2)

      elevator = elevator.nextCommand()
      elevator.command must be equalTo "DOWN"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "DOWN"
    }

    "return DOWN twice when calling from the 2nd floor and 3rd floor and elevator is at 4rd floor" in {
      var elevator = Elevator(currentFloor = 4).call(3).call(2)

      elevator = elevator.nextCommand()
      elevator.command must be equalTo "DOWN"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "DOWN"
    }

    "return UP and then OPEN when elevator is at 2nd floor, a user enter and ask to go to floor 3" in {
      var elevator = Elevator(currentFloor = 2, isOpen = true)
      elevator = elevator.userHasEntered().goTo(3).nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
    }

    "return DOWN and then OPEN when elevator is at 2nd floor, a user enter and ask to go to floor 1" in {
      var elevator = Elevator(currentFloor = 2, isOpen = true)
      elevator = elevator.userHasEntered().goTo(1).nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "DOWN"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasExited()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
    }

    "return DOWN and then OPEN when elevator is at 2nd floor, a user enter and ask to go to floor 1" in {
      var elevator = Elevator(currentFloor = 2, isOpen = true)
      elevator = elevator.userHasEntered().goTo(1).nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "DOWN"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasExited()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
    }

    "take first user to the right floor and then the second user" in {
      var elevator = Elevator()
      elevator = elevator.call(2).call(3).nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.goTo(1)
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "DOWN"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
    }

    "handle two user at the same floor going to the same floor" in {
      var elevator = Elevator()
      elevator = elevator.call(1)
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "NOTHING"
      elevator = elevator.goTo(2)
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasExited()
      elevator = elevator.userHasExited()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
    }

    "handle two user at the same floor going up to different floors" in {
      var elevator = Elevator()
      elevator = elevator.call(1)
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasEntered()
      elevator = elevator.userHasEntered()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "NOTHING"
      elevator = elevator.goTo(2)
      elevator = elevator.goTo(3)
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasExited()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "UP"
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "OPEN"
      elevator = elevator.userHasExited()
      elevator = elevator.nextCommand()
      elevator.command must be equalTo "CLOSE"
    }
  }
}

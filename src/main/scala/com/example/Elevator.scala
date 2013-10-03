package com.example

import List.fill

case class Elevator(calls: List[Boolean] = fill(7)(false), currentFloor: Int = 0, isOpen: Boolean = false, command: String = "NOTHING") {

  def call(floor: Int) = {
    Elevator(calls.updated(floor, true), currentFloor, isOpen, command)
  }

  def userHasEntered() = {
    Elevator(calls.updated(currentFloor, false), currentFloor, isOpen, command)
  }

  def nextCommand() = this match {
    case Elevator(_, _, true, _) => Elevator(calls, currentFloor, false, "CLOSE")
    case Elevator(_, _, _, _) if calls.zipWithIndex.exists(call => call._1 && call._2 == currentFloor) => Elevator(calls, currentFloor, true, "OPEN")
    case Elevator(_, _, _, _) if calls.zipWithIndex.exists(call => call._1 && call._2 > currentFloor) => Elevator(calls, currentFloor + 1, isOpen, "UP")
    case Elevator(_, _, _, _) if calls.exists(call => call) => Elevator(calls, currentFloor - 1, isOpen, "DOWN")
    case _ => Elevator(calls, currentFloor, isOpen, "NOTHING")
  }
}

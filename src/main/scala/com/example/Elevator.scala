package com.example

case class Elevator(callAt: Int = -1, currentFloor: Int = 0, isOpen: Boolean = false) {

  def call(floor: Int) = {
    Elevator(floor, currentFloor, isOpen)
  }

  def userHasEntered() = {
    Elevator(-1, currentFloor, isOpen)
  }

  def nextCommand() : String = this match {
    case Elevator(callAt, currentFloor, _) if callAt != -1 && callAt > currentFloor => "UP"
    case Elevator(callAt, currentFloor, _) if callAt != -1 && callAt == currentFloor => "OPEN"
    case Elevator(callAt, currentFloor, _) if callAt != -1 => "DOWN"
    case Elevator(_, _, true) => "CLOSE"
    case _ => "NOTHING"
  }
}

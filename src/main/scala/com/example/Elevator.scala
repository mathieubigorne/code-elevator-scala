package com.example

import List.fill

case class Elevator(calls: List[Boolean] = fill(7)(false), goTos: List[Int] = Nil, currentFloor: Int = 0, isOpen: Boolean = false, command: String = "NOTHING") {

  def call(floor: Int) = {
    Elevator(calls.updated(floor, true), goTos, currentFloor, isOpen, command)
  }

  def userHasEntered() = {
    Elevator(calls.updated(currentFloor, false), goTos, currentFloor, isOpen, command)
  }

  def goTo(floor: Int) = {
    Elevator(calls, goTos :+ floor, currentFloor, isOpen, command)
  }

  def userHasExited() = {
    Elevator(calls, goTos, currentFloor, isOpen, command)
  }

  def nextCommand() = this match {
    case Elevator(_, _, _, true, _) => Elevator(calls, goTos, currentFloor, false, "CLOSE")
    case Elevator(_, firstGoTo :: _, _, _, _) if firstGoTo > currentFloor => Elevator(calls, goTos, currentFloor + 1, isOpen, "UP")
    case Elevator(_, firstGoTo :: _, _, _, _) if firstGoTo < currentFloor => Elevator(calls, goTos, currentFloor - 1, isOpen, "DOWN")
    case Elevator(_, firstGoTo :: tail, _, _, _) if firstGoTo == currentFloor => Elevator(calls, tail, currentFloor, true, "OPEN")
    case Elevator(_, _, _, _, _) if calls.zipWithIndex.exists(call => call._1 && call._2 == currentFloor) => Elevator(calls, goTos, currentFloor, true, "OPEN")
    case Elevator(_, _, _, _, _) if calls.zipWithIndex.exists(call => call._1 && call._2 > currentFloor) => Elevator(calls, goTos, currentFloor + 1, isOpen, "UP")
    case Elevator(_, _, _, _, _) if calls.exists(call => call) => Elevator(calls, goTos, currentFloor - 1, isOpen, "DOWN")
    case _ => Elevator(calls, goTos, currentFloor, isOpen, "NOTHING")
  }
}

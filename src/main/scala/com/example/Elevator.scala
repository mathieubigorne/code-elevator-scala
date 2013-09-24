package com.example

class Elevator {
  var callAt: Int = -1

  var currentFloor = 0

  var isOpen = false

  def this(current: Int) {
    this()
    currentFloor = current
  }

  def this(current: Int, open: Boolean) {
    this(current)
    isOpen = open
  }

  def call(floor: Int) {
    callAt = floor
  }

  def userHasEntered() {
    callAt = -1
  }

  def nextCommand(): String =
    if (callAt != -1) {
      if (callAt > currentFloor) {
        "UP"
      } else if (callAt == currentFloor) {
        "OPEN"
      } else {
        "DOWN"
      }
    } else if (isOpen) {
      "CLOSE"
    } else {
      "NOTHING"
    }
}

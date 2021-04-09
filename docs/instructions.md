---
title: User Instructions
description: Operations for exercising the implemented functionality.
menu: Build
order: 50
---

## Google Sign In

Users must first use a google account and log in to access the app's functionality.

## Calculator

On the calculator screen, use the buttons to construct valid dice roll formulas. A basic dice
formula might be "2d6" which stands for "roll two 6-sided dice and add the results". More complex
formula can be constructed with the Drop Highest (Drp Hi) and Drop Lowest (Drp Lo) button. For
example, the formula dl(4d6) + 2) represents rolling four 6-sided dice, dropping the lowest result,
summing them together and finally adding 2 for the result. Click the "Trace" button (?) to view the
results of rolling a dice formula at each step of the parse/evaluate algorithm. The save button
allows a user to save a custom die with text rather than numbers on the die faces. 

(NOT IMPLEMENTED) Users may tap the "Load" button to load a custom die on the "dCustom" button. 

## History

The history screen shows a scrollable list of all the dice rolls and results that have been done
from when the app gets installed. A formula can be re-rolled, which causes a new roll to append to
the top of the scrollable list. Each roll item in this list also has a Trace (?) button to view
the steps of the formula

## Dice Tray

(NOT FULLY IMPLEMENTED YET) The Dice Tray screen allows a user to shake their phone causing an
animation effect of multiple dice to spin. The user can lock and unlock the Dice Tray if they want
to prevent accidentally shaking the screen and triggering a new roll. Alternately, simply tapping
the reroll button causes the formula to evaluate and appear immediately with no animation.

## Custom die & Saved formula

* (NOT IMPLEMENTED) The Custom Die screen allows a user to create, edit, and delete custome dice.

* (NOT IMPLEMENTED) Formula can be saved and sorted in a collapsible folder structure for
  organization. Simply tap a saved formula to automatically load and run it on the calculator.

## Settings

* Saved formula auto-roll: If this setting is ON, tapping a saved formula will automatically load
  and run it on the calculator. If this setting is OFF, the formula will load into the input screen
  but it will not run until the user clicks a Roll/Reroll button.
* Roll destination: This radio button setting, determines which screen is opened when the user
  enters a formula and click the "Roll" button. It may be set to Calculator, History, or Dice Tray.  
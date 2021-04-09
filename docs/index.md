---
title: Overview
description: Project summary and intended users
menu: Overview
order: 0
---
## Summary

**Dice Crunch** provides players the ability to simulate polyhedral die rolls with a variety of
useful math operations. Create formulas for multiple die, add, subtract, drop the lowest roll,
and more! Save die formulas that you want to use often for quick access.

Users will be able to:
* Tap buttons to create flexible, die-rolling formulas.
* Save formulas with descriptive names in a customizable directory structure.
* Tap a saved formula to quickly roll its results.
* Create custom die with an unlimited number of descriptive faces.
* Use a custom die with a single tap of a calculator button.
* Scroll through a history of rolls that show the saved formula's name (if present), the formula
  that was used, and the result from that roll.
* Re-roll a past formula by tapping a button on the history screen.
* After creating a formula, watch the animated die try spin up to 20 die at once to generate
  the results.
* Gently shake your phone to re-roll the animation die tray, generating a new result.
* Tap a re-roll button on the die tray to instantly generate a new result without the spinning
  animation.


## Intended users

* Role-playing gamers who want to speed up play

> As a game master I want to streamline the die-rolling of my encounters so that players aren't
> waiting on me. Some of my story encounters involve a lot of abilities, and I save these rolls
> during my preparation time, so I don't have to look it up later.

* Players who play die-based games remotely but don't want to keep all the die they might need

> As an avid boardgame player, I want to play with my friends over Zoom, but we don't all have 
> die that work with every game we play. Dice Crunch allows us each to create custom die so we
> can play all of our favorite boardgames.

## Functionality

* Write formula that simulate die rolls.
* Display calculator-style buttons to enter a formula (e.g. 2d6+3 is roll two six-sided die and
  add three).
* The calculator supports addition, subtraction, multiplication, and division.
* Use odd or impossible polyhedral die (e.g. 7-sided die) .
* Any decimal results are truncated after a few places so users can choose to round up or down.
* Drop highest and drop lowest rolls with all results still displayed.
* Save formulas with descriptive names for quick access.
* Create subfolder structure for saved formulas to organize for different games and characters.
* Create custom die for games with non-numbered die (e.g. "skull", "shield", etc.).
* Supports long-press copy/pasting formula into calculator screen to share with friends.
* Displays result history, automatically loading a limited number of most recent
  rolls. User can review their full results history by continuing to scroll. 
* Repeat any formula in the history display by tapping a re-roll button.
* Grammar for interpreted parsed die-rolling language.
* Regex parser for input validation.
* Watch a die tray animation screen with sound for standard die (d4, d6, d8, d10, d12, d20, d100).
* Custom die animation defaults to 6-sided die with the text on each face.
* Gently shake phone while on the die tray to re-roll for a new result.
* Lock and unlock the die tray prevent accidentally animating a new roll.

## Persistent data

* Subfolder structure
* Formula strings
* Custom die
* Formula and roll history

## Device/external services

* Accelerometer
  * [Documentation](https://developer.android.com/guide/topics/sensors/sensors_motion)
  * Gently shake the phone to animate a graphical die-rolling screen.
  * If device service unavailable, app should limit the user to the other text-based tabs
    and gray out the Dice Tray animation tab. Display a brief snackbar informing the user
    that this feature is not available on their device.

## Stretch goals/possible enhancements

* Dark and light themes
* Animate custom die with user-loaded images.
* Allow user-customized themes through the Settings screen, shareable by a serialized string.
* Create a "Share" button to send a formula or result to contacts through text or email. 
* Integration with a virtual tabletop like Roll20 or Fantasy Grounds.

## Current State of the App

### Readiness

* Creating, saving, and deleting Custom Die is not yet implemented.
* Saving and deleting formula from a folder structure is not yet implemented. 
* The Dice Tray screen is partially implemented. It does not yet show individual animated dice, but
  only the roll result and trace as a placeholder.
* There is no input validation or error handling for invalid formulae--the app simply crashes.
* The app also crashes if no formula has been Rolled, and the user taps the Trace (?) button.

### Aesthetic

* I feel that the app looks a little boring with too much white and grey. Adding colored icons and a few
  muted colors, along with some small highlight colors, could help to liven up the interface.
* The formula input and output TextViews seem like they are taking up too much screen real estate,
  relative to the size of the text. They should probably be compressed, and the font size increased
  to help utilize some of the excess whitespace.
  
### Functional Stretch Goals

From most to least utility/simple to implement:

* Dark and light themes
* Generate a more coherent result string rather than the messy-looking trace, perhaps using regex
  to replace dice roll expressions with an array of rolled results (e.g. 3d6 might yield [4, 1, 3])
* Implement a custom dice screen for saving, creating, and deleting.
* Input validation to prevent malformed formula expressions from being entered.


## License

Copyright 2021 Daniel Arbach

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

> <http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

---

Additional information, covering licenses of incorporated libraries & tools, may be found
at [Notice](notice.md) page.
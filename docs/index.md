---
title: Overview
description: Project summary and intended users
menu: Overview
order: 0
---
## Summary

**Dice Crunch** provides players the ability to simulate polyhedral dice rolls with a variety of useful math operatoions. Create formula for multiple dice, add, subtract, drop the lowest roll, and more! Save dice formulas that you want to use often for quick access.

## Intended users

* Role-playing gamers who want to speed up play

> As a game master I want to streamline the dice-rolling of my encounters so that players aren't waiting on me. Some of my story encounters involve a lot of abilities, and I save these rolls during my preparation time so I don't have to look it up later.

* Players who play dice-based games remotely but don't want to keep all the dice they might need

> As an avid boardgame player, I want to play with my friends over Zoom, but we don't all have dice that work with every game we play. Dice Crunch allows us each to create custom dice so we can play all of our favorite boardgames.

## Functionality

* Write formula that simulate dice rolls
* Display calculator-style buttons to enter formula (e.g. 2d6+3 is roll two six-sided dice and add three)
* Supports addition, subtraction, multiplication, and division
* Use odd or impossible polyhedral dice (e.g. 7-sided dice) 
* Decimal results truncated after a few places so users can choose to round up or down
* Drop highest and drop lowest rolls with all results still displayed
* Perform multiple rolls at the same time
* Save rolls with descriptive names for quick access
* Create custom dice for games with non-numbered dice (e.g. "skull", "shield", etc.). Initially, only available for text-based results.
* Create subfolder structure to organize for different games and characters
* Supports copy/pasting formula into calculator screen to share with friends
* Displays result history for the session, automatically loading a limited number of most recent rolls. User can go back in history by continuing to scroll. 
* Repeat any roll in the history display
* Displays formula roll history from previous sessions
* Grammar for interpreted parsed dice-rolling language
* Regex parser for input validation
* Animation screen with sound for standard dice (d4, d6, d8, d10, d12, d20, d100)

## Persistent data

* Subfolder structure
* Formula strings with
* Custom dice
* Formula history

## Device/external services

* Accelerometer
  * [Documentation](https://developer.android.com/guide/topics/sensors/sensors_motion)
  * Gently flick phone to active a graphical dice-rolling screen
  * If device service unavailable, app will limit user to the text-based fragments

## Stretch goals/possible enhancements

* Dark and light themes
* Animated custom dice with user-loaded images
* Sharing roll results and formula names through group text
* Shareable user-custom themes
* Create a private "chatroom" session with other users that automatically displays the results of your dice rolls and the names of formulae, if present.
* Integration with a virtual tabletop like Roll20 or Fantasy Grounds (if possible)
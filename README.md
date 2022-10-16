# LogoRRR

While exploring the log files is an essential part of troubleshooting, sometimes you may not want to go so deep. In fact, when you get an error, you may simply want to see what happened as fast as possible and in a clear way. LogoRRR is a tool that does precisely that and provides you with a quick way to filter out critical events or other points of interest.

![Screenshot of LogoRRR, version 22.3.0](docs/releases/22.3.0/screenshot-22.3.0.png?raw=true)

## Basic usage

![Shows basic functionality of LogoRRR as animated gif](docs/releases/21.3.1/screencast-21.3.1.gif?raw=true)

For example, ERROR events are visualized as red rectangles, TRACE events as grey rectangles, INFO events as green ones etc. 

You can start LogoRRR via double click from the desktop. Add a log file or directories containg log files via drag'n drop or using the file menu.

## Technology

See [Technology](Technology.md) for a short summary of used technologies.

## Installation 

You can give it a try by downloading a [prebuilt installer for LogoRRR](https://github.com/rladstaetter/LogoRRR/releases/tag/22.3.0) from the releases page. There are installer for Windows and MacOs available.


## Features

- Drag and drop log files to visualize/view them
- Drag and drop a directory to open all contained files
- Handle multiple files in parallel
- Filter log files for entries interactively
- Case insensitive and regex search is supported
- Unix `tail -f` like functionality to watch ongoing events
- LogoRRR remembers last opened files and settings upon restart
- Keyboard shortcuts for more efficient usage 
- Supported operating systems: Windows, MacOsX

## Sponsoring

If you find this project useful you can [buymeacoffee](https://www.buymeacoffee.com/rladstaetter).

Alternatively, just hit the 'star' here on github, or drop me a line at twitter: [@logorrr](https://www.twitter.com/logorrr/). 

A big shoutout goes to @TheJeed and @raumzeitfalle for sponsoring this project and boosting my motivation to continue working on it. Thank you!

I want to thank my employer [NEXTSENSE](https://www.nextsense-worldwide.com/) as well for generously providing vital infrastructure to create this application.

[Advanced Installer](https://www.advancedinstaller.com) sponsors this project too by providing a free license to their product which is used to create windows installers. Thank you.


## License

This software is licensed under Apache-2 License.


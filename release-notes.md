---
layout: default
title: Robolectric Release Notes
---

# Release Notes

-----
## Release 0.9.1 - November 19, 2010
-----

#### Features
- Integrated support for several new Shadow classes from pull requests -- thank you mportuesi!

#### Bug Fixes
- Fixed JSON "stub!" exceptions.

-----
## Release 0.9 - November 17, 2010
-----

#### Features
- Integrated support for several new Shadow classes from pull requests - Thank you contributors!
- Made the default RobolectricTestRunner robust enough to work without modification for most projects
- Improved the extensibility of RobolectricTestRunner for those projects that do need to add more functionality
- Encapsulated most of the fields on the Shadow classes
- Added support for the android.net.Uri class so that it works the same way in tests that it does in production. This is
a departure from the behavior of the Shadow classes. We did this because Uri is a utility class, and it would be
almost impossible to write a useful Shadow class for it.
- Improved documentation
- Added run configurations to make it easier to get Robolectric working out of the box

#### Bug Fixes
- Fixed support for equals(), hashcode(), and toString()
- Improved the documentation for getting Robolectric to work under Eclipse

-----
## Release 0.8 - November 5, 2010
-----

#### Features
- &lt;include&gt; tags apply their attributes to the imported element
- equals(), hashcode(), and toString() can be overridden on all Shadow classes
- Put a link to the Tracker project in the Robolectric User Guide
- Added support for Eclipse

#### Bug Fixes
- ResourceLoader obtained from context, not stored statically
- Instrumented class cache no longer retains stale entries

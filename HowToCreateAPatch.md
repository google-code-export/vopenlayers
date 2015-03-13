# Introduction #
If you are a non member and want contribute something, you need simply start a new issue and attach a patch file with your own changes to it.

The best way to create the patch are the following steps:

```bash

# check out the vopenlayers project and do your changes
# ... if you are finished ...
# 1. switch to the root of your project
cd vopenlayers-read-only
# 2. update your working copy
svn update
# 3. create the patch file
svn diff > myPatch.diff
```
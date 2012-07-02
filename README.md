# versioneer

A clojure library for accessing version information for Leiningen-built jars.

Say you have a command-line Clojure app, and you want to display to the user
what version of your app they're using. No problem, if you're using Leiningen
to build your app's jar.

Add this to your `project.clj`:

```clojure
[trptcolin/versioneer "0.1.0"]
```

Then, in your code, do something like this, where `GROUP-ID` and `ARTIFACT-ID`
are the usual Leiningen/Maven identifiers for your project.

```clojure
user=> (require '[trptcolin.versioneer.core :as version])
nil
user=> (version/get-version "GROUP-ID" "ARTIFACT-ID")
"1.2.3-SNAPSHOT"
```

## Credits

Many thanks to [Michiel Borkent](https://github.com/borkdude) for the initial
implementation as part of [REPL-y](https://github.com/trptcolin/reply), and also
to [Phil Hagelberg](https://github.com/technomancy) for lighting the way.

## License

Copyright © 2012 Colin Jones

Distributed under the Eclipse Public License, the same as Clojure.

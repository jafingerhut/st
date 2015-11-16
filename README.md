# st

Why the extra stack frames with Clojure 1.8.0-RC2 vs. Clojure 1.7.0?

See out-clj18-rc2.txt and compare it to out-clj17.txt.

Note especially the stack frames with line number -1.  What causes those?

## Usage

```clojure
user=> (require '[st.core :as c])

user=> (c/do-it c/m1)
```

## License

Copyright © 2015 Andy Fingerhut

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

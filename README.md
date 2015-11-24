# st

Why the extra stack frames with Clojure 1.8.0-RC2 vs. Clojure 1.7.0?

See out-clj18-rc2.txt and compare it to out-clj17.txt.

Note especially the stack frames with line number -1.  What causes those?

I have confirmed that Clojure 1.8.0-alpha3 and 1.8.0-RC2 behave
identically except for line numbers in source files in terms of the
contents of their stack traces for this code.  Clojure 1.8.0-alpha2
has some differences versus 1.7.0, but not the "-1" line numbers in
the stack trace, and not very many differences as compared to those in
1.8.0-alpha3.

## Update from Nicola Mometto

[link](https://groups.google.com/d/msg/clojure-dev/xDx3qsjhLVs/MGyNh8ZeCQAJ)

With 1.8, whenever a function can be direct linked, it's class
representation will be something like this:

```java
public class .. implements IFn {
..
public static Object invokeStatic() {
 <CODE HERE>
}
public Object invoke() {
        return invokeStatic();
}
..
}
```

while in 1.7, without `invokeStatic`, the bytecode would be directly
in `invoke()`.

I don't think this will have any impact since this 1 method
indirection has been there for `invokePrim` as well.

## Update from Gary Trakhman and Ghadi Shayban


With Clojure 1.8.0-RC2 plus the patch CLJ-1854.patch by Ghadi Shayban
attached to ticket
[CLJ-1854](http://dev.clojure.org/jira/browse/CLJ-1854), the `-1` line
numbers are replaced with real source code line numbers.  See file
`out-clj18-rc2-plus-clj1854-patch.txt` in this repository, as compared
to `out-clj18-rc2.txt`.


## Usage

```clojure
user=> (require '[st.core :as c])

user=> (c/do-it c/m1)
```

## License

Copyright © 2015 Andy Fingerhut

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

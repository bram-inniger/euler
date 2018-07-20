/**
 * Value classes used in this project.
 * <p>
 * Instances of a value-based class:
 * - are final and immutable (though may contain references to mutable objects);
 * - have implementations of equals, hashCode, and toString which are computed solely from the instance's state and not from its identity or the state of any other object or variable;
 * - make no use of identity-sensitive operations such as reference equality (==) between instances, identity hash code of instances, or synchronization on an instances's intrinsic lock;
 * - are considered equal solely based on equals(), not based on reference equality (==);
 * - do not have accessible constructors, but are instead instantiated through factory methods which make no committment as to the identity of returned instances;
 * - are freely substitutable when equal, meaning that interchanging any two instances x and y that are equal according to equals() in any computation or method invocation should produce no visible change in behavior.
 *
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/lang/doc-files/ValueBased.html">https://docs.oracle.com/javase/8/docs/api/java/lang/doc-files/ValueBased.html</a>
 */
package be.inniger.euler.value;

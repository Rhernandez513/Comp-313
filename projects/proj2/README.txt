All of the tests can still pass using a Linked List instead of an Array,
although I believe a Linked List would provide slower performance

If one attempts to use list.Remove(77) such as in testRemove, then one
encounters an IndexOutofBounds Exception

In the testRemoveObject method, the list.remove(value) methods remove the
values from the *index* of the supplied arguement


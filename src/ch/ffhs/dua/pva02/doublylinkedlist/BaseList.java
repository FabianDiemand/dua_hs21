package ch.ffhs.dua.pva02.doublylinkedlist;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Basisimplementierung des List-Interfaces.
 *
 * <p>Alle hier aufgeführten Methoden werfen eine Exception und müssen in der eigenen doppelt
 * verketteten Liste nicht (mehr) implementiert werden.
 */
public abstract class BaseList<E> implements List<E> {

  @Override
  public boolean addAll(Collection<? extends E> c) {
    throw new UnsupportedOperationException("Exception Code MSWN");
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    throw new UnsupportedOperationException("Exception Code ZXCN");
  }

  @Override
  public int indexOf(Object o) {
    throw new UnsupportedOperationException("Exception Code E2XK");
  }

  @Override
  public int lastIndexOf(Object o) {
    throw new UnsupportedOperationException("Exception Code 57CQ");
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException("Exception Code AFEV");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException("Exception Code YYAK");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException("Exception Code 2M92");
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException("Exception Code AIR5");
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException("Exception Code GXTV");
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("Exception Code GJFM");
  }

  @Override
  public ListIterator<E> listIterator() {
    throw new UnsupportedOperationException("Exception Code YMVP");
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException("Exception Code RII3");
  }


}

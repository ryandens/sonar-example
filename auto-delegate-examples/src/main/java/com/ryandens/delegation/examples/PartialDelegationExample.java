package com.ryandens.delegation.examples;

import com.ryandens.delegation.AutoDelegate;
import java.util.logging.Logger;

/** Base interface. */
interface Base {
  String foo();
}

/** Extension of {@link Base}. */
interface Extension extends Base {
  String bar();
}

/**
 * Implementation of {@link Extension} that delegates methods from {@link Base} to some other
 * instance.
 */
@AutoDelegate(Base.class)
class ExtensionImpl extends AutoDelegate_ExtensionImpl implements Extension {
  private final int myIntegerValue;

  ExtensionImpl(final Base delegate) {
    super(delegate);
    myIntegerValue = Integer.valueOf("3").intValue();
  }

  @Override
  public String bar() {
    logger.info(String.format("Processing AutoDelegate annotations %d", myIntegerValue));
    return "bar";
  }

  private static final Logger logger = Logger.getLogger(ExtensionImpl.class.getName());
}

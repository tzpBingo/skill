package org.bigtalkdesignpattern.factoryMethod;

public class UndergraduateFactory implements IFactory {

  public LeiFeng createLeiFeng() {
    return new Graduate();
  }

}

package org.bigtalkdesignpattern.factoryMethod;

public class VolunteerFactory implements IFactory {

  public LeiFeng createLeiFeng() {
    return new Volunteer();
  }

}

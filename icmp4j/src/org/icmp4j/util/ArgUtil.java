package org.icmp4j.util;

/**
 * Internet Control Message Protocol for Java (ICMP4J)
 * http://www.icmp4j.org
 * Copyright 2009 and beyond, icmp4j
 * <p/>
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation as long as:
 * 1. You credit the original author somewhere within your product or website
 * 2. The credit is easily reachable and not burried deep
 * 3. Your end-user can easily see it
 * 4. You register your name (optional) and company/group/org name (required)
 * at http://www.icmp4j.org
 * 5. You do all of the above within 4 weeks of integrating this software
 * 6. You contribute feedback, fixes, and requests for features
 * <p/>
 * If/when you derive a commercial gain from using this software
 * please donate at http://www.icmp4j.org
 * <p/>
 * If prefer or require, contact the author specified above to:
 * 1. Release you from the above requirements
 * 2. Acquire a commercial license
 * 3. Purchase a support contract
 * 4. Request a different license
 * 5. Anything else
 * <p/>
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, similarly
 * to how this is described in the GNU Lesser General Public License.
 * <p/>
 * User: Sal Ingrilli
 * Date: May 23, 2014
 * Time: 7:52:21 PM
 */
public class ArgUtil {
  
  /**
   * Returns true if the given arg is found
   * @param args
   * @param arg
   * @return boolean
   */
  public static boolean findArgument (
    final String[] args,
    final String arg) {

    for (int argIndex = 0; argIndex < args.length; argIndex++) {
      if (args[argIndex].equals (arg)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Returns the value of the given arg
   * findArgValue ("-arg") returns "value" for args "-arg value"
   * findPropertyArgValue ("-arg") returns "value" for args "-arg=value"
   * @param args
   * @param arg
   * @return String
   */
  public static String findArgValue (
    final String[] args,
    final String arg) {

    // look for it
    for (int argIndex = 0; argIndex < args.length; argIndex++) {

      // arg found
      if (!args[argIndex].equals (arg)) {
        continue;
      }
        
      // look for its alue
      argIndex++;
      if (argIndex < args.length) {
        return args[argIndex];
      }
    }
    
    // not found
    return null;
  }
  
  /**
   * Returns an int representing the given arg
   * Returns null if not found
   * @param args
   * @param argName
   * @return int
   */
  public static Boolean findArgValueAsBoolean (
    final String[] args,
    final String argName,
    final Boolean defaultValue) {

    final String value = findArgValue (args, argName);
    return value != null ?
      Boolean.parseBoolean (value) :
      defaultValue;
  }
  
  /**
   * Returns an int representing the given arg
   * Returns null if not found
   * @param args
   * @param argName
   * @return int
   */
  public static Integer findArgValueAsInt (
    final String[] args,
    final String argName,
    final Integer defaultValue) {

    final String value = findArgValue (args, argName);
    return value != null ?
      Integer.parseInt (value) :
      defaultValue;
  }
}
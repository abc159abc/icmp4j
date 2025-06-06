package org.icmp4j.platform;

import junit.framework.TestCase;
import org.icmp4j.IcmpTraceRouteRequest;
import org.icmp4j.IcmpTraceRouteUtil;
import org.icmp4j.IcmpTraceRouteResponse;

/**
 * ShortPasta Foundation
 * http://www.shortpasta.org
 * Copyright 2009 and beyond, Sal Ingrilli at the ShortPasta Software Foundation
 * <p/>
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation as long as:
 * 1. You credit the original author somewhere within your product or website
 * 2. The credit is easily reachable and not burried deep
 * 3. Your end-user can easily see it
 * 4. You register your name (optional) and company/group/org name (required)
 * at http://www.shortpasta.org
 * 5. You do all of the above within 4 weeks of integrating this software
 * 6. You contribute feedback, fixes, and requests for features
 * <p/>
 * If/when you derive a commercial gain from using this software
 * please donate at http://www.shortpasta.org
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
 * Date: Oct 10, 2014
 * Time: 12:57:24 AM
 */
public class IcmpTraceRouteUtilTest extends TestCase {

  /**
   * Tests the normal trace route
   */
  public void test_executeTraceRoute_www_google_com () {

    // request
    final IcmpTraceRouteRequest request = new IcmpTraceRouteRequest ();
    request.setHost ("www.goodbyeftp.com");
    request.setPacketSize (32);
    request.setTimeout (5 * 1000);
    request.setTtl (30);
    
    // delegate
    final IcmpTraceRouteResponse response = IcmpTraceRouteUtil.executeTraceRoute (request);
    System.out.println ("response: " + response);
  }
  
  /**
   * Tests the normal trace route
   */
  public void test_executeTraceRoute_www_google_com_100 () {
    
    // delegate
    for (int index = 0; index < 100; index ++) {
      System.out.println ("Running test " + index);
      test_executeTraceRoute_www_google_com ();
    }
  }
}
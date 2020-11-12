/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.commons.compress2.archivers.zip;

import org.apache.commons.compress2.archivers.zip.X000A_NTFS;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class X000A_NTFSTest {

    @Test
    public void simpleRountrip() throws Exception {
        final X000A_NTFS xf = new X000A_NTFS();
        xf.setModifyJavaTime(new Date(0));
        // one second past midnight
        xf.setAccessJavaTime(new Date(-11644473601000l));
        xf.setCreateJavaTime(null);
        final byte[] b = xf.getLocalFileDataData();

        final X000A_NTFS xf2 = new X000A_NTFS();
        xf2.parseFromLocalFileData(b, 0, b.length);
        assertEquals(new Date(0), xf2.getModifyJavaTime());
        assertEquals(new Date(-11644473601000l), xf2.getAccessJavaTime());
        assertEquals(null, xf2.getCreateJavaTime());
    }
}

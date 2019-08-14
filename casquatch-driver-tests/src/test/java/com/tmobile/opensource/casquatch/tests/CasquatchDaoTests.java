/*
 * Copyright 2018 T-Mobile US, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tmobile.opensource.casquatch.tests;

import com.tmobile.opensource.casquatch.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CasquatchDaoTests {

    private static class InvalidEntity extends AbstractCasquatchEntity {
        @Override
        public AbstractCasquatchEntity keys() {
            return null;
        }
    }

    private static CasquatchDao casquatchDao;

    @BeforeClass
    public static void setup() {
        casquatchDao = new CasquatchTestDaoBuilder().withEmbedded().withTestKeyspace("junittest").build();
    }

    @Test(expected = DriverException.class)
    public void testInvalidEntity() {
        casquatchDao.save(InvalidEntity.class,new InvalidEntity());
    }

    @Test
    public void testGetKeyspace() {
        assertEquals(casquatchDao.getKeyspace(),"junittest");
    }

    @Test
    public void testGetBuilder() {
        assert(casquatchDao.builder() instanceof CasquatchDaoBuilder);
    }
    
}
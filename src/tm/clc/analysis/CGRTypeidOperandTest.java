//     Copyright 1998--2010 Michael Bruce-Lockhart and Theodore S. Norvell
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License. 
// You may obtain a copy of the License at 
//
//     http://www.apache.org/licenses/LICENSE-2.0 
//
// Unless required by applicable law or agreed to in writing, 
// software distributed under the License is distributed on an 
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
// either express or implied. See the License for the specific language 
// governing permissions and limitations under the License.

package tm.clc.analysis;

import tm.clc.ast.TypeNode;

/**
 * Tests for <em>typeid</em> operand. This can impact expression building, 
 * with C++'s <em>sizeof</em> operator for example. 
 */
public class CGRTypeidOperandTest extends CGRTest {

    /**
     * Is the first operand a <code>TypeNode</code> ?
     *
     * @param exp contains the operand to test
     * @return <code>true</code> if <code>operands[0]</code> contains 
     * a <code>TypeNode</code>, <code>false</code> otherwise.
     */
    public boolean applies (ExpressionPtr exp) {
        return (exp.is (TypeNode.class, 0));
    }
}

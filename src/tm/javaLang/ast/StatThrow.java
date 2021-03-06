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

package tm.javaLang.ast;

import java.util.Hashtable;

import tm.clc.ast.ExpressionNode;
import tm.clc.ast.StatementNode;
import tm.clc.ast.StatementNodeLink;
import tm.clc.ast.StatementNodeVisitor;
import tm.interfaces.Datum;
import tm.interfaces.SourceCoords;
import tm.javaLang.datum.PointerDatum;
import tm.utilities.Assert;
import tm.virtualMachine.AbruptCompletionStatus;
import tm.virtualMachine.ExpressionEvaluation;
import tm.virtualMachine.VMState;

/**
 * <p>Title: The Teaching Machine</p>
 * <p>Description: </p>
 * <p>Company: Memorial University</p>
 * @author Theodore Norvell
 * @version 1.0
 */

public class StatThrow extends StatementNode {
    ExpressionNode exp ;
    StatementNodeLink nextLink ;

    public StatThrow (SourceCoords coords, int varDepth, ExpressionNode e) {
        super("throw", coords, varDepth) ;
        nextLink = new StatementNodeLink() ;
        this.exp = e ;
    }

    public StatementNodeLink next() { return nextLink ; }

    public void step( VMState vms) {
        if( vms.top().at(this) == null ) {

            // First step
            trimVariables( varDepth, vms ) ;
            vms.top().map(this, new Integer(0)) ;
            setScratchMark( vms ) ;
            ExpressionEvaluation ee = new ExpressionEvaluation( vms, exp ) ;
            vms.push( ee ) ; }

        else {

            // Second step

            Datum exprValue = vms.getExpressionResult() ;
            Assert.apology( exprValue instanceof PointerDatum,
                            "Internal error, pointer expected" ) ;

            AbruptCompletionStatus acs = new  ThrowCompletionStatus( exprValue ) ;
            vms.abruptCompletion( acs ) ; } }

    public void beVisited( StatementNodeVisitor visitor ) {
        visitor.visit( this ) ;
        nextLink.beVisited( visitor ) ;
    }

    public String toString( Hashtable h ) {
        return "    (" + h.get( this ) + ") StatThrow line="+getCoords()+" depth="+getVarDepth()+"\n"
               +exp.ppToString(4, 80)
               + "      ---> ("+ h.get( nextLink.get() )+")\n" ;
    }
}
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

/*
 * Created on 9-May-2005
 */
package tm.javaLang.ast;

import tm.backtrack.BTTimeManager;
import tm.clc.analysis.Declaration;
import tm.clc.analysis.ScopedName;
import tm.clc.ast.TyAbstractClass;
import tm.clc.datum.AbstractDatum;
import tm.clc.datum.AbstractObjectDatum;
import tm.interfaces.TypeInterface;
import tm.javaLang.datum.ClassDatum;
import tm.utilities.Assert;
import tm.virtualMachine.MemRegion;
import tm.virtualMachine.Memory;
import tm.virtualMachine.VMState;

/**
 * @author mpbl
 *
 */

/*******************************************************************************
Class: TyClass

Overview:
This class represents the Java class type.

Review:          xxxx xx xx     xxxxxxxxxxxxx
*******************************************************************************/

public class TyMetaClass extends TyAbstractClass implements TyJava {

    public TyMetaClass(ScopedName nm) {
        super(nm.getName());
    }

    protected AbstractObjectDatum constructDatum(int add, AbstractDatum p,
                                                 Memory m, String n, BTTimeManager timeMan) {
        return new ClassDatum(add, p, m, n, this, timeMan);
    }

    public String typeId() { return "Meta-"+getTypeString(); }


    /** 
     * @see tm.interfaces.TypeInterface#equal_types(tm.interfaces.TypeInterface)
     */
    public boolean equal_types(TypeInterface t) {
        return t==this;
    }

    /** 
     * @see tm.javaLang.ast.TyJava#elementId()
     */
    public String elementId() {
        Assert.check( false ) ;
        return null;
    }

    /** 
     * @see tm.javaLang.ast.TyJava#isWidening(tm.javaLang.ast.TyJava)
     */
    public boolean isReachableByWideningFrom(TyJava fromType) {
        return false;
    }
}
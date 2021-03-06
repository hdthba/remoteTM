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

package tm.cpp.ast;

import tm.clc.ast.TyAbstractRef;
import tm.clc.ast.TypeNode;
import tm.clc.datum.AbstractDatum;
import tm.cpp.datum.RefDatum;
import tm.interfaces.TypeInterface;
import tm.virtualMachine.MemRegion;
import tm.virtualMachine.VMState;

public class TyRef extends TyAbstractRef implements TyCpp {

    public TyRef( ) {
        super() ; }

	public TyRef(TypeNode pointeeTp) {
		super(pointeeTp) ; }

	public AbstractDatum makeMemberDatum(VMState vms, int address, AbstractDatum parent, String name) {
		RefDatum d = new RefDatum( address, parent,
		                    vms.getMemory(), name , this, vms.getStore(),
                            vms.getTimeManager()) ;
		return d ; }
	
	public AbstractDatum makeDatum(VMState vms, MemRegion mr, String name) {
		int address = mr.findSpace( RefDatum.size ) ;
		RefDatum d = new RefDatum( address, null,
		                      vms.getMemory(), name, this, vms.getStore(),
                              vms.getTimeManager() ) ;
		vms.getStore().addDatum(d) ;
		return d ; }
        
	public boolean equal_types( TypeInterface t ) {
		return t instanceof TyRef
		   && getPointeeType().equal_types( ((TyRef)t).getPointeeType() ) ; }
	
	public String getTypeString(){
	    return "ref to "+getPointeeType().getTypeString() ; }
	
	public String typeId() { return typeId( "", false ) ; }
	
	public String typeId( String seed, boolean lastWasLeft ) {
	    seed = "&"+seed;
	    return ((TyCpp)getPointeeType()).typeId( seed, true ); 
	}
}
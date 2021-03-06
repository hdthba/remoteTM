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

import tm.clc.ast.TypeNode;
import tm.clc.datum.AbstractDatum;
import tm.cpp.analysis.Cpp_Specifiers;
import tm.cpp.datum.SignedCharDatum;
import tm.interfaces.TypeInterface;
import tm.utilities.Assert;
import tm.virtualMachine.MemRegion;
import tm.virtualMachine.VMState;

public class TySignedChar extends TypeNode implements TyIntegral, TyCpp {

	private TySignedChar() {
		super() ; }

	private static TySignedChar [] qualified = new TySignedChar [4];

	/**
	 * Returns a <code>TySignedChar</code> instance with default (none/auto) 
	 * cv-qualification.
	 * @return a <code>TySignedChar</code> instance
	 * @see Cpp.Analysis.SpecifierSet
	 */
	public static TySignedChar get () {
		return get (0);
	}

	/**
	 * Returns a <code>TySignedChar</code> instance with the indicated 
	 * cv-qualification.
	 * @param cv_qual a flag indicating cv-qualification as follows:
	 * 0 : none (auto), 1 : const, 2 : volatile, 3 : const volatile.
	 * @return a <code>TySignedChar</code> instance
	 * @see Cpp.Analysis.SpecifierSet
	 */
	public static TySignedChar get (int cv_qual) {
		TySignedChar instance = null;
		if (cv_qual >= qualified.length) {
			Assert.apology (INVALID_CV_QUALIFICATION);
		} else { 
			if (qualified [cv_qual] == null) {
				qualified [cv_qual] = new TySignedChar ();
				qualified [cv_qual].setAttributes (cv_qual);
			}
			instance = qualified [cv_qual];
		}
		return instance;
	}

	public TypeNode getQualified (int cv_qual) { return get (cv_qual); }

	public AbstractDatum makeMemberDatum(VMState vms, int address, AbstractDatum parent, String name) {
		SignedCharDatum d = new SignedCharDatum( address, parent, vms.getMemory(), name, vms.getTimeManager() ) ;
		return d ; }

	public AbstractDatum makeDatum(VMState vms, MemRegion mr, String name) {
		int address = mr.findSpace( SignedCharDatum.size ) ;
		SignedCharDatum d = new SignedCharDatum( address, null, vms.getMemory(), name, vms.getTimeManager() ) ;
		vms.getStore().addDatum(d) ;
		return d ; }

	public boolean equal_types( TypeInterface t ) {
		return t instanceof TySignedChar ; }
	
	public String getTypeString () { return "signed char"; }

	public int getNumBytes() { return SignedCharDatum.size ; }
	
	public String typeId() { return typeId( "", false ) ; }
	
	public String typeId( String seed, boolean lastWasLeft ) {
	    seed = "signed char"+seed ;
	    if( (getAttributes() & Cpp_Specifiers.CVQ_CONST) != 0 )
	        seed = "const "+seed ;
	    return seed ;
	}	
}

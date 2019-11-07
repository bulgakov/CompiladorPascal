/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package type;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author mijail
 */
@XmlType(namespace="type")
@XmlSeeAlso({
    BaseType.class,
    ProcType.class,
    Undefined.class,
    Void.class})
public abstract class Type {
    @XmlTransient
    public abstract boolean same(Type e);
    @XmlTransient
    public abstract boolean assignable(Type e);
    @Override
    public abstract String toString();
}

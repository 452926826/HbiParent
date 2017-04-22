package hbi.core.bom.dto;

/**
 * Auto Generated By Hap Code Generator
 **/

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.hand.hap.mybatis.annotation.ExtensionAttribute;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.hand.hap.system.dto.BaseDTO;

@ExtensionAttribute(disable = true)
@Table(name = "bom_bom")
public class BomSync extends BaseDTO {
    @Id
    @GeneratedValue
    private Long bomId;

    private String ida2a2;

    private String linkida2a2;

    private String childmasterida2a2;

    private String id;


    private String state;

    private String total;

    private String linenum;

    private String occurrence;

    private String amount;

    private String unit;

    private String childnum;

    private String childname;

    private String partstate;

    private String status;

    private String version;

    private String linkweight;

    private String source;

    private String isvirtual;

    private String priory;

    private String isboard;

    private String replacement;

    private String isborrow;

    private String iskey;

    private String discription;

    private String isselected;

    private String view;

    private String material;

    private String producttype;

    private String classification;

    private String sapversion;

    private String drawingsize;

    private String cadtype;

    private String drawingtype;

    private String specification;

    private String marlevel;

    private String weight;

    private String parentid;

    private Boolean hasChildren = false;

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getParentid() {
        return parentid;
    }

    public BomSync setParentid(String parentid) {
        this.parentid = parentid;
        return this;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public Long getBomId() {
        return bomId;
    }

    public void setIda2a2(String ida2a2) {
        this.ida2a2 = ida2a2;
    }

    public String getIda2a2() {
        return ida2a2;
    }

    public void setLinkida2a2(String linkida2a2) {
        this.linkida2a2 = linkida2a2;
    }

    public String getLinkida2a2() {
        return linkida2a2;
    }

    public void setChildmasterida2a2(String childmasterida2a2) {
        this.childmasterida2a2 = childmasterida2a2;
    }

    public String getChildmasterida2a2() {
        return childmasterida2a2;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setLinenum(String linenum) {
        this.linenum = linenum;
    }

    public String getLinenum() {
        return linenum;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setChildnum(String childnum) {
        this.childnum = childnum;
    }

    public String getChildnum() {
        return childnum;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public String getChildname() {
        return childname;
    }

    public void setPartstate(String partstate) {
        this.partstate = partstate;
    }

    public String getPartstate() {
        return partstate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setLinkweight(String linkweight) {
        this.linkweight = linkweight;
    }

    public String getLinkweight() {
        return linkweight;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setIsvirtual(String isvirtual) {
        this.isvirtual = isvirtual;
    }

    public String getIsvirtual() {
        return isvirtual;
    }

    public void setPriory(String priory) {
        this.priory = priory;
    }

    public String getPriory() {
        return priory;
    }

    public void setIsboard(String isboard) {
        this.isboard = isboard;
    }

    public String getIsboard() {
        return isboard;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setIsborrow(String isborrow) {
        this.isborrow = isborrow;
    }

    public String getIsborrow() {
        return isborrow;
    }

    public void setIskey(String iskey) {
        this.iskey = iskey;
    }

    public String getIskey() {
        return iskey;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }

    public void setIsselected(String isselected) {
        this.isselected = isselected;
    }

    public String getIsselected() {
        return isselected;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    public void setSapversion(String sapversion) {
        this.sapversion = sapversion;
    }

    public String getSapversion() {
        return sapversion;
    }

    public void setDrawingsize(String drawingsize) {
        this.drawingsize = drawingsize;
    }

    public String getDrawingsize() {
        return drawingsize;
    }

    public void setCadtype(String cadtype) {
        this.cadtype = cadtype;
    }

    public String getCadtype() {
        return cadtype;
    }

    public void setDrawingtype(String drawingtype) {
        this.drawingtype = drawingtype;
    }

    public String getDrawingtype() {
        return drawingtype;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSpecification() {
        return specification;
    }

    public void setMarlevel(String marlevel) {
        this.marlevel = marlevel;
    }

    public String getMarlevel() {
        return marlevel;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

}
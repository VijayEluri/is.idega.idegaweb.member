package is.idega.idegaweb.member.presentation;

import java.util.Hashtable;

import com.idega.core.data.Address;
import com.idega.core.data.Country;
import com.idega.presentation.IWContext;
import com.idega.presentation.Table;
import com.idega.presentation.text.Text;
import com.idega.presentation.ui.PostalCodeDropdownMenu;
import com.idega.presentation.ui.TextInput;
import com.idega.user.business.GroupBusiness;
import com.idega.user.data.Group;
import com.idega.user.presentation.UserGroupTab;

/**
 *@author     <a href="mailto:thomas@idega.is">Thomas Hilbig</a>
 *@version    1.0
 */
public class GroupOfficeAddressTab extends UserGroupTab {
  
  private TextInput streetField;
  private TextInput cityField;
  private TextInput provinceField;
  private PostalCodeDropdownMenu postalCodeField;
  private TextInput countryField;
  private TextInput poBoxField;

  private static final String streetFieldName = "UMstreet";
  private static final String cityFieldName = "UMcity";
  private static final String provinceFieldName = "UMprovince";
  private static final String postalCodeFieldName = PostalCodeDropdownMenu.IW_POSTAL_CODE_MENU_PARAM_NAME;
  private static final String countryFieldName = "UMcountry";
  private static final String poBoxFieldName = "UMpoBox";

  private Text streetText;
  private Text cityText;
  private Text provinceText;
  private Text postalCodeText;
  private Text countryText;
  private Text poBoxText;

  public GroupOfficeAddressTab() {
    this.setName("Address");
  }
  
  public GroupOfficeAddressTab(Group group)  {
    this();
     // do not store the group because this tab instance will be also used by other groups
     // (see setGroupId() !)
     setGroupId(((Integer)group.getPrimaryKey()).intValue());
  }
  

  public void initializeFieldNames(){

  }


  public void initializeFieldValues(){

    if( fieldValues==null ) fieldValues = new Hashtable();
    
    /*fieldValues.put(streetFieldName,"");
    fieldValues.put(cityFieldName,"");
    fieldValues.put(provinceFieldName,"");
    fieldValues.put(postalCodeFieldName,"");
    fieldValues.put(countryFieldName,"");
    fieldValues.put(poBoxFieldName,"");*/

  }

  public void updateFieldsDisplayStatus(){
    String street = (String)fieldValues.get(streetFieldName);
    String city = (String)fieldValues.get(cityFieldName);
    String province = (String)fieldValues.get(provinceFieldName);
    String postalId = (String)fieldValues.get(postalCodeFieldName);
    String country = (String)fieldValues.get(countryFieldName);
    String poBox = (String)fieldValues.get(poBoxFieldName);
      
    if( street!=null ) streetField.setContent(street);
    if( city!=null ) cityField.setContent(city);
  if( province!=null) provinceField.setContent(province);
  
  System.out.println("PostalCodeId = "+postalId);
  if( postalId!=null && !postalId.equals("") ) postalCodeField.setSelectedElement(Integer.parseInt(postalId));

    if(country!=null) countryField.setContent(country);
    if( poBox!=null ) poBoxField.setContent(poBox);

  }


  public void initializeFields(){
    streetField = new TextInput(streetFieldName);
    streetField.setLength(20);


    cityField = new TextInput(cityFieldName);
    cityField.setLength(20);

    provinceField = new TextInput(provinceFieldName);
    provinceField.setLength(20);

//only works for Iceland
    if(postalCodeField==null){
      postalCodeField = new PostalCodeDropdownMenu();
      postalCodeField.setCountry("Iceland");//hack
    }
    
    countryField = new TextInput(countryFieldName);
    countryField.setLength(20);
    countryField.setDisabled(true);

    poBoxField = new TextInput(poBoxFieldName);
    poBoxField.setLength(10);

  }

  public void initializeTexts(){
    streetText = new Text("Street");
    streetText.setFontSize(fontSize);

    cityText = new Text("City");
    cityText.setFontSize(fontSize);

    provinceText = new Text("Province");
    provinceText.setFontSize(fontSize);

    postalCodeText = new Text("Postal");
    postalCodeText.setFontSize(fontSize);

    countryText = new Text("Country");
    countryText.setFontSize(fontSize);

    poBoxText = new Text("P.O.Box");
    poBoxText.setFontSize(fontSize);

  }


  public void lineUpFields(){
    this.resize(1,1);

    Table addressTable = new Table(2,4);

//    FramePane fpane = new FramePane();

    addressTable.setWidth("100%");
    addressTable.setCellpadding(0);
    addressTable.setCellspacing(0);
    addressTable.setHeight(1,rowHeight);
    addressTable.setHeight(2,rowHeight);
    addressTable.setHeight(3,rowHeight);
    addressTable.setHeight(4,rowHeight);
    addressTable.setWidth(1,"70");

    addressTable.add(this.streetText,1,1);
    addressTable.add(this.streetField,2,1);
    addressTable.add(this.cityText,1,2);
    addressTable.add(this.cityField,2,2);
    addressTable.add(this.provinceText,1,3);
    addressTable.add(this.provinceField,2,3);
    addressTable.add(this.countryText,1,4);
    addressTable.add(this.countryField,2,4);

    this.add(addressTable);
//    fpane.add(addressTable);

    Table addressTable2 = new Table(4,1);

    addressTable2.setWidth("100%");
    addressTable2.setCellpadding(0);
    addressTable2.setCellspacing(0);
    addressTable2.setHeight(1,rowHeight);
    addressTable2.setWidth(1,"70");
    addressTable2.setWidth(2,"70");
    addressTable2.setWidth(3,"70");

    addressTable2.add(this.postalCodeText, 1, 1);
    addressTable2.add(this.postalCodeField, 2, 1);
    addressTable2.add(this.poBoxText, 3, 1);
    addressTable2.add(this.poBoxField, 4, 1);

    this.add(addressTable2);
//    fpane.add(addressTable2);
//    this.add(fpane);

  }


  public boolean collect(IWContext iwc){

    if(iwc != null){
      String street = iwc.getParameter(this.streetFieldName);
      String city = iwc.getParameter(this.cityFieldName);
      String province = iwc.getParameter(this.provinceFieldName);
      String postal = iwc.getParameter(this.postalCodeFieldName);
      String country = iwc.getParameter(this.countryFieldName);
      String poBox = iwc.getParameter(this.poBoxFieldName);

      if(street != null){
        fieldValues.put(this.streetFieldName,street);
      }
      if(city != null){
        fieldValues.put(this.cityFieldName,city);
      }
      if(province != null){
        fieldValues.put(this.provinceFieldName,province);
      }
      if(postal != null){
        fieldValues.put(this.postalCodeFieldName,postal);
      }
      if(country != null){
        fieldValues.put(this.countryFieldName,country);
      }
      if(poBox != null){
        fieldValues.put(this.poBoxFieldName,poBox);
      }

      this.updateFieldsDisplayStatus();

      return true;
    }
    return false;
  }

  public boolean store(IWContext iwc){

      Integer groupId = new Integer(getGroupId());
      String street = iwc.getParameter(streetFieldName);
      
      if( street!=null ){ 
        try{
          Integer postalCodeId = null;
          String postal = iwc.getParameter(postalCodeFieldName);
          if(postal!=null) postalCodeId = new Integer(postal);
          String country = iwc.getParameter(countryFieldName);
            String city = iwc.getParameter(cityFieldName);
            String province = iwc.getParameter(provinceFieldName);      
            String poBox = iwc.getParameter(poBoxFieldName);
    
    
          this.getGroupBusiness(iwc).updateGroupMainAddressOrCreateIfDoesNotExist(groupId,street,postalCodeId,country,city,province,poBox);
          

        }catch(Exception e){
          e.printStackTrace();
          return false;
        }
      }
    
    return true;
                
  }

  public void initFieldContents(){
    try{
      GroupBusiness groupBiz = getGroupBusiness(this.getEventIWContext());
      Group group = groupBiz.getGroupByGroupID(getGroupId());
      Address addr = groupBiz.getGroupMainAddress(group);

      boolean hasAddress = false;
      if(addr != null){
        hasAddress = true;
      }
      

      
    if( hasAddress){
            /** @todo remove this fieldValues bullshit!**/
      String street = addr.getStreetAddress();
      int code = addr.getPostalCodeID();     
      Country country = addr.getCountry();
      String countryName = null;
      if(country!=null) countryName = country.getName();
      String city = addr.getCity();
      String province = addr.getProvince();   
      String poBox = addr.getPOBox();
      
        if( street!=null ) fieldValues.put(streetFieldName, street );
        if( city!=null ) fieldValues.put(cityFieldName, city );
        if ( province!=null ) fieldValues.put(provinceFieldName, province );
        if ( code!=-1 ) fieldValues.put(postalCodeFieldName, String.valueOf(code) );
        if ( countryName!=null ) fieldValues.put(countryFieldName, countryName );
        if ( poBox!=null ) fieldValues.put(poBoxFieldName, poBox);
    }
      
      updateFieldsDisplayStatus();

    }catch(Exception e){
      e.printStackTrace();  
      System.err.println("AddressInfoTab error initFieldContents, userId : " + getGroupId());
    }
  }




} // Class AddressInfoTab
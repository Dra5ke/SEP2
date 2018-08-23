package LFD.domain.model;

public class UserInfo
{
   private String name;
   private String address;
   private Date DOB;
   private String email;

   public UserInfo(String name, String address, Date DOB, String email)
   {
      this.name = name;
      this.address = address;
      this.DOB = DOB;
      this.email = email;

   }

   public void setName(String name)
   {
      this.name = name;

   }

   public void setAddress(String address)
   {
      this.address = address;

   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getName()
   {
      return this.name;
   }

   public String getAddress()
   {
      return this.address;

   }

   public Date getDate()
   {
      return this.DOB;
   }

   public String getEmail()
   {
      return this.email;
   }

}

# PAMS - Product Authentication & Management System
### Created by:
#### Allie Arsenault - Front End Engineer
#### Earl "Duke" Bozarth - Java Engineer
#### Matt Brown - Java Engineer
#### Cameron Oakley - Java Engineer

## Deploy Locally:
1. Clone repo
2. <code> bower install </code>
3. Run the jar file from your console: 
<code> java -jar PAMS-2015-0.0.1-SNAPSHOT.jar </code>
4. Open localhost:8080
5. For demo purposes, any username/password will give access to the ADMIN section of PAMS so that the full functionality can be explored.


# Requirements Documentation
### Elevator Pitch: PAMS ensures the authenticity of brand name products thereby protecting brands, retailers, and consumers alike.  PAMS strives to discontinue the circulation of counterfeit items in partnership with major brands and retailers.

#### Technology
  * Spring
  * Javascript using Angular
  * Postgres
  * HTML / CSS (Sass)
  * Google Maps API
  
#### MVP Features
1. User Hierarchy
  * Admin (PAMS employee)
  * Company (Ex: Titleist, Nike, Ping)
  * Retailer (Ex: Golfsmith)
  
2. Verification of product using serial number/barcode
  * Test conditions for both positive & negative results
  * Tracking each counterfeit item
  
3. Timestamp of user entries
  * Tracking Retailers who use PAMS service: time, user and location
  
4. Importing files
  * Bulk import
  * Single product import

#### Roadmap
  * Google Maps API - location tracking
  *  Key card scanner
  * Personal profile for fraud sellers
  * Price breakdowns
  * Stats/graphs for fraud items including when they’re reported (to see trends)

--

### User Stories

#### User Hierarchy (Admin/Company/Retailer)
**Value Statement**
  * To insure that data can not be modified or tampered with by regulating login through a hierarchal system
  
**Acceptance Criteria**
  * Admin is at the highest level and has access to all functionality
  * Companies have the ability to import products, search through their products & verify the authenticity of products
  * Retailers have the ability to verify the authenticity of a product

#### Verification of product using serial number/barcode
**Value Statement**
  * Provides assurance that product is genuine
  
**Acceptance Criteria**
  * Serial number or identification number & model must be a 100% match with the number provided by a verified producer of that product.
  * The conditions met are either true or false. If true, the item is genuine. If false, there will be an alert & the item will be automatically tracked.

#### Timestamp of user entries
**Value Statement**
  * Provides indisputable record of when the product was entered and who entered it.
  
**Acceptance Criteria**
  * A time and date is stored every time a counterfeit item is entered into the system
  * The timestamp connects the user’s entry to the currently logged in user

#### Importing files
**Value Statement**
  * Allows company to directly and easilty CRUD inventory
  
**Acceptance Criteria**
  * Proper authority logged in
  * Correct formatted file is upload
  * Information is securely stored 


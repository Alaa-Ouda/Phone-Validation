import {IterateEnum} from './iterate-enum';
import {PhoneStateEnum} from "../enums/PhoneStateEnum";
import {CountryEnum} from "../enums/CountryEnum";

export class Vars {
  public static EnumPhoneStateStatus() { return IterateEnum.getNamesAndValues(PhoneStateEnum); }
  public static EnumCountry() { return IterateEnum.getNamesAndValues(CountryEnum); }
}

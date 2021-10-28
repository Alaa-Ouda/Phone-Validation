export class IterateEnum {
  public static getNamesAndValues(e: any) {
    return this.getNames(e).map(function (_name) { return { name: _name, value: e[_name] }; });
  }

  private static getNames(e: any) {
    return Object.keys(e).filter(function (key) { return isNaN(+key); });
  }
}

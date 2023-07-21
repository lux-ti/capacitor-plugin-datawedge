export interface DataWedgePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}

# capacitor-plugin-datawedge

Integration with Zebra Datawedge

## Install

```bash
npm install capacitor-plugin-datawedge
npx cap sync
```

## API

<docgen-index>

* [`addListener(T, ...)`](#addlistenert)
* [`createProfile(...)`](#createprofile)
* [`alert()`](#alert)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### addListener(T, ...)

```typescript
addListener<T extends keyof DataWedgeEvents>(eventName: T, listenerFunc: (event: DataWedgeEvents[T]) => void) => PluginListenerHandle
```

| Param              | Type                                                |
| ------------------ | --------------------------------------------------- |
| **`eventName`**    | <code>T</code>                                      |
| **`listenerFunc`** | <code>(event: DataWedgeEvents[T]) =&gt; void</code> |

**Returns:** <code><a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### createProfile(...)

```typescript
createProfile(options: { name: string; }) => Promise<any>
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ name: string; }</code> |

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### alert()

```typescript
alert() => Promise<any>
```

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### DataWedgeCommandResult

| Prop            | Type                |
| --------------- | ------------------- |
| **`result`**    | <code>string</code> |
| **`command`**   | <code>string</code> |
| **`info`**      | <code>string</code> |
| **`commandId`** | <code>string</code> |


#### DataWedgeScanResult

| Prop            | Type                |
| --------------- | ------------------- |
| **`scanData`**  | <code>string</code> |
| **`labelType`** | <code>string</code> |
| **`source`**    | <code>string</code> |


### Type Aliases


#### DataWedgeEvents

<code>{ ready: {} action_result: <a href="#datawedgecommandresult">DataWedgeCommandResult</a>; scan_result: <a href="#datawedgescanresult">DataWedgeScanResult</a>; }</code>

</docgen-api>

# capacitor-plugin-datawedge

Integration with Zebra Datawedge

## Install

```bash
npm install capacitor-plugin-datawedge
npx cap sync
```

## API

<docgen-index>

* [`createProfile(...)`](#createprofile)
* [`addListener(E, ...)`](#addlistenere)
* [`removeAllListeners()`](#removealllisteners)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### createProfile(...)

```typescript
createProfile(options: { name: string; }) => any
```

| Param         | Type                           |
| ------------- | ------------------------------ |
| **`options`** | <code>{ name: string; }</code> |

**Returns:** <code>any</code>

--------------------


### addListener(E, ...)

```typescript
addListener<E extends keyof DataWedgeEvents>(eventName: E, listenerFunc: (event: DataWedgeEvents[E]) => void) => PluginListenerHandle
```

| Param              | Type                                                |
| ------------------ | --------------------------------------------------- |
| **`eventName`**    | <code>E</code>                                      |
| **`listenerFunc`** | <code>(event: DataWedgeEvents[E]) =&gt; void</code> |

**Returns:** <code><a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### removeAllListeners()

```typescript
removeAllListeners() => void
```

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                      |
| ------------ | ------------------------- |
| **`remove`** | <code>() =&gt; any</code> |


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

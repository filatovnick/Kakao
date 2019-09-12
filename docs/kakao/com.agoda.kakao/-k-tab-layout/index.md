[kakao](../../index.md) / [com.agoda.kakao](../index.md) / [KTabLayout](./index.md)

# KTabLayout

`class KTabLayout : `[`KBaseView`](../-k-base-view/index.md)`<`[`KTabLayout`](./index.md)`>, `[`TabLayoutActions`](../-tab-layout-actions/index.md)`, `[`TabLayoutAssertions`](../-tab-layout-assertions/index.md)

View with TabLayoutActions and TabLayoutAssertions

**See Also**

[TabLayoutActions](../-tab-layout-actions/index.md)

[TabLayoutAssertions](../-tab-layout-assertions/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `KTabLayout(function: `[`ViewBuilder`](../-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>`KTabLayout(parent: Matcher<`[`View`](https://developer.android.com/reference/android/view/View.html)`>, function: `[`ViewBuilder`](../-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>`KTabLayout(parent: DataInteraction, function: `[`ViewBuilder`](../-view-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [view](../-k-base-view/view.md) | `open val view: ViewInteraction` |

### Inherited Functions

| Name | Summary |
|---|---|
| [getSelectedItem](../-tab-layout-actions/get-selected-item.md) | `open fun getSelectedItem(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns the currently selected item id |
| [invoke](../-k-base-view/invoke.md) | `operator fun invoke(function: `[`T`](../-k-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Operator that allows usage of DSL style |
| [isTabSelected](../-tab-layout-assertions/is-tab-selected.md) | `open fun isTabSelected(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Checks if TabLayout have selected tab with given index |
| [perform](../-k-base-view/perform.md) | `infix fun perform(function: `[`T`](../-k-base-view/index.md#T)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`T`](../-k-base-view/index.md#T)<br>Infix function for invoking lambda on your view |
| [selectTab](../-tab-layout-actions/select-tab.md) | `open fun selectTab(index: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Selects tab at given index |

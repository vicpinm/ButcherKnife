 <p align="center">
  <img width="50%" src ="/logo.png" />
</p>

**Lightweight binding engine based annotation processing. Bind your view model to your views throught annotations, with the help of annotation parameters with which you can format and with which the binding process.**

### Basic example

Starting from an example model named 'Book', we have to bind its properties to different views in our layout. 
Book entity has a title, an image (which requires a placeholder while the image is being downloaded), and a timestamp named 'date' of type Long (which requires a custom format). 
```kotlin
class Book(val date: Long, var title: String, val image: String)
```

With ButcherKnife, you can annotate each field of your entity, so that they will be bound to your views in your xml automatically, without the usual boilerplate related with the binding process. 
```kotlin
class Book(

@BindDate(id = R.id.date, format = R.string.dateformat)
val date: Long,

@BindText(id = R.id.title)
var title: String,

@BindImage(id = R.id.img, placeholder = R.drawable.placeholder)
val image: String)
```

In this example, you can see three different annotations. Each annotation requires an id parameter in order to connect your view with the annotated field.

Let's take a look at each annotation:

* ```@BindDate```: It takes the ```date``` field and formats it with the date format specified in the string resource passed as argument in this annotation. 
* ```@BindText```: It takes the ```title``` field and binds it to the view with id ```R.id.title```
* ```@BindImage```: It takes the image url in the field ```image``` an downloads it with picasso. In the meanwhile, it shows the placeholder ```R.drawable.placeholder```

As you can see, with a few annotations, you get a lot of work done for you. And this is only a sample of the power of ButcherKnife.

## Available annotations
Next you can see all types of annotations available and the arguments you can set to each of them. 

### @BindText
* **Allowed field types for this annotation: String, Int, Long, Float and Double**
* **Target view: TextView, EditText... (any View with a setText(string) method)**

| Argument      | Type | Required  | Description          |
| :------------- |:-----| :---------| :--------------------|
| **id**           | Int  | Yes       | Target view. View id to be initialized with the value of the annotated field. |
| **template**       | Int  | No        | String resource id to be used as template. This string resource should contain one placeholder like ```%s```or ```%d```. The value contained in the field annotated with ```@BindText``` will be placed in that placeholder.   |
| **visibilityIfEmpty**       | Int  | No        | You can control the visibility of the view if you set set this argument with this annotation. For example, with ``` @BindText(id = ..., visibilityIfEmpty = View.GONE) ```, your view will be set as GONE if your field is null or empty. |
| **isHtml**           | Boolean  | No       | If your field contains html tags, you can parse them like you would do with ```Html.fromHtml(...)```. You have to set in your annotation ```isHtml = true``` if you need that behaviour. |
| **observeChanges**           | Boolean  | No       | If your target view is an EditText or inherits from EditText, and you want to update your field annotated with @BindText when the content of your EditText changes, set this argument to true. Default false. |

### @BindDate
**Allowed field types for this annotation: Long and Date**
* **Target view: TextView, EditText... (any View with a setText(string) method)**

| Argument      | Type | Required  | Description          |
| :------------- |:-----| :---------| :--------------------|
| **id**           | Int  | Yes       | View id to be initialized with the value of the annotated field. |
| **format**       | Int  | Yes        | String resource id to be used as date pattern. ButcherKnife uses ```DateFormat``` to convert your date to a String using the pattern you provide.  |
| **template**       | Int  | No        | String resource id to be used as template. This string resource should contain one placeholder like ```%s```or ```%d```. The value contained in the field annotated with ```@BindText``` will be placed in that placeholder.   |
| **visibilityIfEmpty**       | Int  | No        | You can control the visibility of the view if you set set this argument with this annotation. For example, with ``` @BindText(id = ..., visibilityIfEmpty = View.GONE) ```, your view will be set as GONE if your field is null or empty. |

### @BindImage
* **Allowed field types for this annotation: String**
* **Target view: ImageView or any view that inherits from ImageView**

| Argument      | Type | Required  | Description          |
| :------------- |:-----| :---------| :--------------------|
| **id**           | Int  | Yes       | View id to be initialized with the value of the annotated field. |
| **placeholder**       | Int  | No        | Placeholder to show while Picasso is loading your image.  |
| **cropped**       | Boolean  | No        | Whether your image will use scale type cropped or not (default true).  |
| **isFile**       | Boolean  | No        | Set this attribute to true if you are loading your image from an imageFile (deafult false).  |
| **isCircle**       | Boolean  | No        | Set this attribute to true if you want to transform your image with a circle shape.    |
| **cornerRadius**       | Int  | No        | Dimension resource. If you want to round the corners of your ImageView, specify a dimension resource with the raidus, like ```cornerRadius = R.dimen.small_corner```   |
| **cornerType**       | CornerType  | No        | If you set a cornerRadius, all corners will be rounded. If you want to specify the corners you want to round, set a corner type value, like ```cornerType = CornerType.TOP```  |
| **visibilityIfEmpty**       | Int  | No        | You can control the visibility of the view if you set set this argument with this annotation. For example, with ``` @BindText(id = ..., visibilityIfEmpty = View.GONE) ```, your view will be set as GONE if your field is null or empty. |

### @BindBool
* **Allowed field types for this annotation: Boolean**
* **Target view: CompoundButton or any view that inherits from CompoundButton (RadioButton, CheckBox...)**

| Argument      | Type | Required  | Description          |
| :------------- |:-----| :---------| :--------------------|
| **id**           | Int  | Yes       | View id to be initialized with the value of the annotated field. |
| **observeChanges**           | Boolean  | No       | If your want to listen changes in the check status of your target view, set this argument to true. Default false. |


## Download 

Grab via Gradle:

```groovy
repositories {
    mavenCentral()
}

implementation "com.github.vicpinm:butcherknife:1.0.2"
kapt "com.github.vicpinm:butcherknife-processor:1.0.2"

```


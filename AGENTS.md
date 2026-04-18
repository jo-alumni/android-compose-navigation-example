# AGENTS.md

- 質問には必ず日本語で回答してください
- コードの修正を行う際には必ずプランを立てて修正方針を提示し、了承を得てから修正を行ってください
- マルチモジュール構成です。主要モジュールは `settings.gradle.kts` を基準に把握してください（例: `:app`, `:app:core`, `:app:feature:posts`, `:app:feature:postDetail`, `:app:feature:todo`, `:core:common`, `:core:data`, `:core:domain`, `:core:api`, `:core:local:datastore`, `:core:local:database`）。
- アプリ層の feature モジュールは `libs.plugins.navigationTest.feature` を使用し、Compose + Kotlin + Hilt + Orbit を前提に実装されています（例: `app/feature/posts/build.gradle.kts`, `build-logic/src/main/kotlin/convention/AndroidFeaturePlugin.kt`）。
- 新規 feature 追加時は `build.gradle.kts` に加えて `*Screen.kt`、`*ViewModel.kt`、`contract/*State.kt`、`contract/*Event.kt`、`navigation/*Navigation.kt` を同一 feature 配下に揃えてください（例: `app/feature/todo/src/main/kotlin/com/example/navigationtest/app/feature/todo/`）。
- 新規 feature 追加の最小テンプレート（ファイル名のみ）: `build.gradle.kts` / `*Screen.kt` / `*ViewModel.kt` / `contract/*State.kt` / `contract/*Event.kt` / `navigation/*Navigation.kt`
- 画面状態管理は Orbit MVI パターンです。`contract` 配下に State/Event を置き、ViewModel は `ContainerHost` を実装する実装に揃えてください（例: `app/feature/posts/contract/PostsState.kt`, `app/feature/posts/contract/PostsEvent.kt`, `app/feature/posts/PostsViewModel.kt`）。
- Navigation は型付き destination（`@Serializable`）で定義しています。画面追加時は feature ごとの `navigation/*Navigation.kt` に destination/composable/navigate 拡張をまとめてください（例: `app/feature/posts/navigation/PostsNavigation.kt`, `app/feature/postDetail/navigation/PostDetailNavigation.kt`）。
- 画面追加後は `app/src/main/kotlin/com/example/navigationtest/app/AppNavHost.kt` に `*Screen` 拡張を登録し、遷移は feature 側 `navigate*` 拡張を呼び出してください（例: `homeScreen`, `tweetDetailScreen`, `todoScreen` / `navigateToPostDetail`, `navigateTodo`）。
- データ境界は `core/domain` の repository interface と `core/data` の実装 + Hilt `@Binds` です。新規データ取得は interface を `core/domain` に追加し、実装を `core/data`、DI バインドを `core/data/di/DataModule.kt` に追加してください。
- Repository 実装追加時は `core/data/datarepository/*DataRepository.kt` を作成し、必要な DataSource を `core/data/datasource` に追加したうえで実体モジュール側で `@Binds` してください（API 側: `core/api/src/main/kotlin/com/example/navigationtest/core/api/di/ApiModule.kt`、DataStore 側: `core/local/datastore/src/main/kotlin/com/example/navigationtest/core/local/datastore/di/LocalModule.kt`）。
- API の base URL は `API_BASE_URL` を使用します。`local.properties` または環境変数に値が必要です（参照: `app/build.gradle.kts`, `build-logic/src/main/kotlin/primitive/Environment.kt`, `app/src/main/kotlin/com/example/navigationtest/app/di/ApplicationModule.kt`）。
- 変更確認は必要最小限で `./gradlew :app:assembleDebug` を優先し、静的解析は `./gradlew :app:lint` と `./gradlew :app:ktlintCheck` を使用してください（ktlint プラグイン適用: `app/build.gradle.kts`）。
- 実行順序は `./gradlew :app:assembleDebug` → `./gradlew :app:lint` → `./gradlew :app:ktlintCheck` を基本にしてください。

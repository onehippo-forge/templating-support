# templating-support

This is a project to support various view templating technologies other than FreeMarker
in Hippo CMS Delivery tier web application.

This project is experimental and being implemented by volunteer members in the community, not part of official products.
Therefore please respect the volunteers when asking questions or asking for helps.

For details, please visit the project homepage at [onehippo-forge.github.io/templating-support](https://onehippo-forge.github.io/templating-support/).

# Documentation (Local)

The documentation can generated locally by this command:

```bash
$ mvn clean install
$ mvn clean site
```

The output is in the ```target/site/``` directory by default. You can open ```target/site/index.html``` in a browser.

# Documentation (GitHub Pages)

Documentation is available at [https://onehippo-forge.github.io/content-export-import/](https://onehippo-forge.github.io/content-export-import/).

You can generate the GitHub pages only from ```master``` branch by this command:

```bash
$ mvn clean install
$ find docs -name "*.html" -exec rm {} \;
$ mvn -Pgithub.pages clean site
```

The output is in the ```docs/``` directory by default. You can open ```docs/index.html``` in a browser.

You can push it and GitHub Pages will be served for the site automatically.

## For Contributors

- Please ask and discuss if you want to collaborate, contribute or review on anything through Hippo Community Forum.
- Specific view templating dependencies should be added as ```optional``` and end projects should include the dependency explicitly.
- Once we get a relatively stable version, we'll cut the initial release, with online site documentations including release notes.
- All the contributors are encouraged to *fork* and create *pull requests* for any contributions.
- When submitting a pull request, please make sure to add proper license headers. See existing sources.

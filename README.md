This is a fork of [duckling] (https://github.com/wit-ai/duckling_old), with some features for Spanish language.

* Add functions for invoke from java.
* Add suport for dates with separators "/ - _  ." (like 11_11-2017 11_11.2017 11/11-2017)

## Step by step

* Install Leiningen
    * https://leiningen.org/#install
* Install gpg4win
    * https://www.gpg4win.org/
* Create or modified rules 
* Create a new duckling version (project.clj)
    * current version 0.4.10
* Compile and create .jar of duckling
    * lein compile
    * lein javac
    * lein uberjar
* Deploy
    * configure credentials.clj

        ` 
        {#"http://184.73.254.191/nexus/content/.*"
            {:username "deployment" :password "aZuRRo"}}
        `

    * configure profiles.clj

        `
        {:user
            {
  	           :java-cmd "C:\\Program Files\\Java\\jdk1.6.0_45\\bin\\java.exe"
  	           :plugins [
  	           ]
  	           :signing {:gpg-key "lmoctezuma@bluemessaging.com"
                :deploy-repositories [["http://184.73.254.191/nexus/content/.*" {:creds :gpg}]]} 
            }
        }
        `
    * Encrypt a file with `gpg -e -r lmoctezuma@bluemessaging.com credentials.clj`
    * lein deploy nexus-release

# **Important:** 
For deploy the policy must be Release or SnapShot

# You can upload directly:
`curl -v -u deployment:aZuRRo --upload-file duckling-0.4.10-standalone.jar http://184.73.254.191/nexus/content/repositories/thirdparty/com/bm/duckling/0.4.10/ `

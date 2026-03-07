# ap1-dependency-injection
 # TP — Injection de Dépendances (Spring Framework)

## Description

Ce TP illustre le principe d'**injection de dépendances** (Dependency Injection) en Java, en explorant quatre approches différentes : instanciation statique, instanciation dynamique par réflexion, injection via Spring XML, et injection via les annotations Spring.

L'architecture respecte le **couplage faible** : les couches communiquent uniquement à travers des interfaces, ce qui permet de substituer les implémentations sans modifier le code métier.

---

## Structure du projet

```
ap1-dependency-injection/
├── pom.xml
├── config.txt                          # Fichier de configuration pour Pres2 (réflexion)
└── src/main/
    ├── java/net/oussama/
    │   ├── dao/
    │   │   ├── IDao.java               # Interface couche DAO
    │   │   └── DaoImpl.java            # Implémentation DAO (base de données)
    │   ├── ext/
    │   │   └── DaoImplV2.java          # Implémentation DAO alternative (capteur)
    │   ├── metier/
    │   │   ├── IMetier.java            # Interface couche métier
    │   │   └── MetierImpl.java         # Implémentation métier
    │   └── pres/
    │       ├── Pres1.java              # Injection statique (new)
    │       ├── Pres2.java              # Injection dynamique (réflexion Java)
    │       ├── PresSpringXML.java      # Injection via Spring XML
    │       └── PresSpringAnnotation.java # Injection via annotations Spring
    └── resources/
        └── config.xml                  # Configuration Spring XML
```

---

## Architecture

Le projet suit une architecture en **3 couches** :

```
[Présentation] → [Métier (IMetier)] → [DAO (IDao)]
```

- **IDao** expose la méthode `getData()` qui retourne une valeur double.
- **IMetier** expose la méthode `calcul()` qui réalise un calcul à partir de la donnée fournie par le DAO.
- **MetierImpl** dépend de `IDao` (couplage faible) et réalise le calcul : `t * 12 * π/2 * cos(t)`.

---

## Les 4 approches d'injection

### 1. Instanciation statique — `Pres1.java`

L'implémentation concrète est instanciée directement dans le code avec `new`. La dépendance est injectée via le constructeur.

```java
DaoImplV2 d = new DaoImplV2();
MetierImpl metier = new MetierImpl(d);
System.out.println("RES = " + metier.calcul());
```

**Inconvénient :** couplage fort avec l'implémentation concrète. Tout changement d'implémentation nécessite de modifier le code source.

---

### 2. Instanciation dynamique par réflexion — `Pres2.java`

Les noms des classes sont lus depuis le fichier `config.txt`. La réflexion Java permet d'instancier les classes et d'injecter la dépendance sans recompiler.

**`config.txt` :**
```
net.oussama.ext.DaoImplV2
net.oussama.metier.MetierImpl
```

```java
Scanner scanner = new Scanner(new File("config.txt"));
Class cDao = Class.forName(scanner.nextLine());
IDao d = (IDao) cDao.newInstance();

Class cMetier = Class.forName(scanner.nextLine());
IMetier metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(d);
System.out.println("Res= " + metier.calcul());
```

**Avantage :** changer d'implémentation se fait en modifiant uniquement `config.txt`, sans recompilation.

---

### 3. Injection via Spring XML — `PresSpringXML.java`

Spring gère lui-même l'instanciation et l'injection grâce au fichier `config.xml`.

**`config.xml` :**
```xml
<bean id="d" class="net.oussama.dao.DaoImpl"/>
<bean id="metier" class="net.oussama.metier.MetierImpl">
    <property name="dao" ref="d"/>
</bean>
```

```java
ApplicationContext springContext = new ClassPathXmlApplicationContext("config.xml");
IMetier metier = (IMetier) springContext.getBean(IMetier.class);
System.out.println("RES" + metier.calcul());
```

**Avantage :** la configuration est externalisée dans un fichier XML. Spring crée et lie les beans automatiquement.

---

### 4. Injection via annotations Spring — `PresSpringAnnotation.java`

Spring scanne le package `net.oussama` et détecte les beans grâce aux annotations `@Component` et `@Qualifier`.

- `DaoImpl` est annoté `@Component("d")`
- `DaoImplV2` est annoté `@Component("d2")`
- `MetierImpl` est annoté `@Component("metier")` et utilise `@Qualifier("d")` pour sélectionner l'implémentation DAO à injecter

```java
ApplicationContext ctx = new AnnotationConfigApplicationContext("net.oussama");
IMetier metier = ctx.getBean(IMetier.class);
System.out.println("RES=" + metier.calcul());
```

**Avantage :** aucune configuration XML, tout est déclaré directement dans le code via les annotations.

---

## Compilation et exécution

```bash
# Compiler le projet
mvn compile

# Exécuter une des classes de présentation (exemple avec PresSpringAnnotation)
mvn exec:java -Dexec.mainClass="net.oussama.pres.PresSpringAnnotation"
```

> **Note :** Pour `Pres2`, le fichier `config.txt` doit se trouver dans le répertoire d'exécution.

---

## Comparaison des approches

| Approche              | Recompilation nécessaire | Configuration externalisée | Flexibilité |
|-----------------------|:------------------------:|:--------------------------:|:-----------:|
| Statique (Pres1)      | ✅ Oui                   | ❌ Non                     | Faible      |
| Réflexion (Pres2)     | ❌ Non                   | ✅ `config.txt`            | Moyenne     |
| Spring XML            | ❌ Non                   | ✅ `config.xml`            | Bonne       |
| Spring Annotations    | ❌ Non                   | ✅ (dans le code)          | Bonne       |

---




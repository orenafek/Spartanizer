package il.org.spartan.refactoring.java;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.refactoring.utils.*;

class Term {
  private final boolean negative;
  public final Expression expression;

  Term(final boolean minus, final Expression expression) {
    negative = minus;
    this.expression = expression;
  }

  static Term plus(final Expression e) {
    return new Term(false, e);
  }

  static Term minus(final Expression e) {
    return new Term(true, e);
  }

  boolean negative() {
    return negative;
  }

  Expression asExpression() {
    if (!negative)
      return expression;
    final PrefixExpression $ = expression.getAST().newPrefixExpression();
    $.setOperand(expression);
    $.setOperator(wizard.MINUS1);
    return $;
  }

  public boolean positive() {
    return !negative;
  }
}